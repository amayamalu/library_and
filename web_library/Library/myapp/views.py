import datetime

from django.core.files.storage import FileSystemStorage
from django.http import JsonResponse

from .models import *

# Create your views here.
def and_login(request):
    u=request.POST['u']
    p=request.POST['p']
    res=login.objects.filter(username=u, password=p)
    if res.exists():
        res=res[0]
        return JsonResponse({'status':"ok", "lid":res.id, "type":res.usertype})
    else:
        return JsonResponse({'status':"no"})


#               ADMIN
def and_view_course(request):
    res=course.objects.all()
    data=[]
    for i in res:
        data.append({
            'id':i.id, "cname":i.coursename, "dept":i.department
        })
    return JsonResponse({'status':"ok", "data":data})

def and_delete_course(request):
    cid=request.POST['cid']
    course.objects.filter(id=cid).delete()
    return JsonResponse({'status':"ok"})

def and_add_course(request):
    cname=request.POST['cname']
    dept=request.POST['dept']
    obj=course()
    obj.coursename=cname
    obj.department=dept
    obj.save()
    return JsonResponse({'status': "ok"})

def and_view_stud(request):
    res = student.objects.all()
    data = []
    for i in res:
        data.append({
            'id': i.id, "sname": i.studname, "eml":i.email, "phn":i.phone, "sem":i.sem, "crs":i.COURSE.coursename
        })
    return JsonResponse({'status': "ok", "data": data})

def and_delete_stud(request):
    sid = request.POST['sid']
    res=student.objects.get(id=sid)
    login.objects.filter(id=res.LOGIN.id).delete()
    return JsonResponse({'status': "ok"})

def and_add_student(request):
    name=request.POST['name']
    eml=request.POST['eml']
    phn=request.POST['phn']
    cid=request.POST['cid']
    sem=request.POST['sem']

    obj=login()
    obj.username=eml
    obj.password=phn
    obj.usertype="student"
    obj.save()

    obj1=student()
    obj1.studname=name
    obj1.email=eml
    obj1.phone=phn
    obj1.COURSE_id=cid
    obj1.sem=sem
    obj1.LOGIN=obj
    obj1.save()
    return JsonResponse({'status': "ok"})


def and_view_category(request):
    res=category.objects.all()
    data=[]
    for i in res:
        data.append({
            'id':i.id, "cat":i.cname
        })
    return JsonResponse({'status': "ok", "data":data})

def and_delete_category(request):
    cid = request.POST['cid']
    category.objects.filter(id=cid).delete()
    return JsonResponse({'status': "ok"})

def and_add_cat(request):
    cat=request.POST['cat']
    obj=category()
    obj.cname=cat
    obj.save()
    return JsonResponse({'status': "ok"})

def and_view_book(request):
    res=books.objects.all()
    data=[]
    for i in res:
        data.append({
            'id':i.id, "name":i.bookname, "author":i.author, "edition":i.edition, "image":i.image,
            "status":i.status, "cat":i.CATEGORY.cname
        })
    return JsonResponse({'status': "ok", "data":data})

def and_delete_book(request):
    bid = request.POST['bid']
    books.objects.filter(id=bid).delete()
    return JsonResponse({'status': "ok"})


def and_view_category_admin(request):
    res = category.objects.all()
    data = []
    for i in res:
        data.append({
            'id': i.id, "cat": i.cname
        })
    return JsonResponse({'status': "ok", "data": data})


def and_add_book(request):
    name=request.POST['name']
    author=request.POST['author']
    edition=request.POST['edition']
    # status = request.POST['status']
    category = request.POST['cat']
    image=request.FILES['pic']
    d=datetime.datetime.now().strftime("%Y%m%d-%H%M%S")
    fs=FileSystemStorage()
    fs.save(r"C:\Users\amaya\PycharmProjects\Library\myapp\static\images\\"+d+'.jpg',image)
    path="/static/images/"+d+'.jpg'
    obj=books()
    obj.CATEGORY_id=category
    obj.bookname=name
    obj.author=author
    obj.edition=edition
    obj.status="Available"
    obj.image=path
    obj.save()
    return JsonResponse({"status":'ok'})


def view_book_search(request):
    search=request.POST['search']
    res=books.objects.filter(bookname=search)
    data=[]
    for i in res:
        data.append({
            "name":i.bookname,
            "author":i.author,
            "edition":i.edition,
            "status":i.status,
            "image":i.image,
        })
    return JsonResponse({"status":"ok","data":data})

def view_studentsss(request):
    res=student.objects.all()
    data=[]
    for i in res:
        data.append({

                'id': i.id, "sname": i.studname, "eml": i.email, "phn": i.phone, "sem": i.sem,
                "crs": i.COURSE.coursename
        })

    return JsonResponse({"status":"ok","data":data})


def and_issue_book(request):
    sid=request.POST['sid']
    bid=request.POST['bid']
    due=request.POST['da']
    obj=borrow_logs()
    obj.BOOK_id=bid
    obj.STUDENT_id=sid
    obj.status="issued"
    obj.date=datetime.datetime.now().strftime("%Y-%m-%d")
    obj.due_date=due
    obj.save()
    return JsonResponse({"status":"ok"})



def and_borrowed_books(request):
    res=borrow_logs.objects.all()
    data=[]
    for i in res:
        data.append({
            "id":i.id,
            "date":i.date,
            "due_date":i.due_date,
            "status":i.status,
            "book_info":i.BOOK.bookname+"\n"+i.BOOK.author+"\n"+i.BOOK.edition,
            # "author":i.BOOK.author,
            # "edition":i.BOOK.edition,
            "student_info":i.STUDENT.studname+"\n"+i.STUDENT.email+"\n"+i.STUDENT.phone,
            # "stud_email":i.STUDENT.email,
            # "stud_phone":i.STUDENT.phone,
        })
    return JsonResponse({"status":"ok","data":data})


def return_borrowed_books(request):
    bid=request.POST['bid']
    borrow_logs.objects.filter(id=bid).update(status="returned",return_date=datetime.datetime.now().strftime("%d-%m-%Y"))
    return JsonResponse({"status":"ok"})




#               STUDENT

def and_view_booksss(request):
    cat=request.POST['cat']
    res=books.objects.filter(CATEGORY=cat)
    data=[]
    for i in res:
        data.append(
            {
                "name":i.bookname,
                "author":i.author,
                "image":i.image,
                "status":i.status,
                "edition":i.edition,
                "cat":i.CATEGORY.cname,
            }
        )
    print(data)
    return JsonResponse({"status":"ok", "data": data})

def and_borrow_books(request):
    lid = request.POST['lid']
    re = borrow_logs.objects.filter(STUDENT__LOGIN=lid, status="issued")
    data = []
    for i in re:
        data.append(
            {
                "date": i.date,
                "due_date": i.due_date,
                "status": i.status,
                "book_info": i.BOOK.bookname + "\n" + i.BOOK.author + "\n" + i.BOOK.edition,

            }
        )
    return JsonResponse({"status": "ok", "data": data})

def and_previous_books(request):
    lid=request.POST['lid']
    re=borrow_logs.objects.filter(STUDENT__LOGIN=lid,status="returned")
    data=[]
    for i in re:
        data.append(
            {
                "date":i.date,
                "due_date":i.due_date,
                "status":i.status,
                "book_info":i.BOOK.bookname+"\n"+i.BOOK.author+"\n"+i.BOOK.edition,


             }
        )
    return JsonResponse({"status":"ok","data":data})


def stud_category(request):
    res = category.objects.all()
    data = []
    for i in res:
        data.append({
            'id': i.id, "cat": i.cname
        })
    return JsonResponse({'status': "ok", "data": data})
