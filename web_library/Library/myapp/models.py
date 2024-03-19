from django.db import models

# Create your models here.
class login(models.Model):
    username=models.CharField(max_length=100)
    password=models.CharField(max_length=100)
    usertype=models.CharField(max_length=100)


class course(models.Model):
    coursename=models.CharField(max_length=100)
    department=models.CharField(max_length=100)

class category(models.Model):
    cname=models.CharField(max_length=100)


class books(models.Model):
    bookname=models.CharField(max_length=100)
    author=models.CharField(max_length=100)
    edition=models.CharField(max_length=100)
    image=models.CharField(max_length=100)
    status=models.CharField(max_length=100)
    CATEGORY=models.ForeignKey(category, on_delete=models.CASCADE)


class student(models.Model):
    studname=models.CharField(max_length=100)
    email=models.CharField(max_length=100)
    phone=models.CharField(max_length=100)
    sem=models.CharField(max_length=100)
    COURSE=models.ForeignKey(course, on_delete=models.CASCADE)
    LOGIN=models.ForeignKey(login, on_delete=models.CASCADE)


class borrow_logs(models.Model):
    date=models.CharField(max_length=100)
    due_date=models.CharField(max_length=100)
    status=models.CharField(max_length=100)
    return_date=models.CharField(max_length=100,default=1)
    BOOK=models.ForeignKey(books, on_delete=models.CASCADE)
    STUDENT=models.ForeignKey(student, on_delete=models.CASCADE)