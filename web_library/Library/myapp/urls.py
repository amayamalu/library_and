"""
URL configuration for Library project.

The `urlpatterns` list routes URLs to views. For more information please see:
    https://docs.djangoproject.com/en/4.2/topics/http/urls/
Examples:
Function views
    1. Add an import:  from my_app import views
    2. Add a URL to urlpatterns:  path('', views.home, name='home')
Class-based views
    1. Add an import:  from other_app.views import Home
    2. Add a URL to urlpatterns:  path('', Home.as_view(), name='home')
Including another URLconf
    1. Import the include() function: from django.urls import include, path
    2. Add a URL to urlpatterns:  path('blog/', include('blog.urls'))
"""

from django.urls import path
from myapp import views

urlpatterns = [
    path('and_login', views.and_login),

    path('and_view_course', views.and_view_course),
    path('and_delete_course', views.and_delete_course),
    path('and_add_course', views.and_add_course),
    path('and_view_category', views.and_view_category),
    path('and_delete_category', views.and_delete_category),
    path('and_add_cat', views.and_add_cat),
    path('and_view_stud', views.and_view_stud),
    path('and_delete_stud', views.and_delete_stud),
    path('and_add_student', views.and_add_student),
    path('and_view_book', views.and_view_book),
    path('and_add_book', views.and_add_book),
    path('and_delete_book', views.and_delete_book),
    path('view_book_search', views.view_book_search),
    path('and_issue_book', views.and_issue_book),
    path('and_borrowed_books', views.and_borrowed_books),
    path('view_studentsss', views.view_studentsss),
    path('and_view_category_admin', views.and_view_category_admin),
    path('return_borrowed_books', views.return_borrowed_books),



    ###############################################################
    path('and_view_booksss', views.and_view_booksss),
    path('and_borrow_books', views.and_borrow_books),
    path('and_previous_books', views.and_previous_books),
    path('stud_category', views.stud_category),
]
