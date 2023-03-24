from django.shortcuts import render, get_object_or_404
from django.http import HttpResponse
from .models import *

# Create your views here.

def index(request):
    return render(request, 'index.html')

def view_all_books(request):
    all_books = Book.objects.all() # obtain a list of all books
    return render(request, 'index.html', {'books':all_books}) # pass this list to a webpage

def view_by_genre(request, bookgenre):
    genre_book = Book.objects.all().filter(genre=bookgenre)
    return render(request, 'category.html', {'book':genre_book})

def view_single_book(request, bookid):
    single_book = get_object_or_404(Book, id=bookid)
    borrows = Borrowed.objects.filter(book=bookid, is_returned=False)
    returned = Borrowed.objects.filter(book=bookid, is_returned=True)
    return render(request, 'single_book.html', {'book':single_book, 'borrowed': borrows, 'returned': returned,})

def view_all_customers(request):
    all_customers = Customer.objects.all() # obtain a list of all books
    return render(request, 'all_customers.html', {'customers':all_customers}) # pass this list to a webpage

def view_customer(request, customerid):
    customer = get_object_or_404(Customer, id=customerid)
    borrowed = Borrowed.objects.filter(customer=customerid, is_returned=False)
    returned = Borrowed.objects.filter(customer=customerid, is_returned=True)
    return render(request, 'customer_info.html', {'customer':customer, 'borrowed': borrowed, 'returned': returned})
