from django.shortcuts import render, get_object_or_404
from django.http import HttpResponse
from .models import *
from django.http import JsonResponse # import the jsonresponse object
from rest_framework import viewsets
from .serializers import *

## viewset for customers
class CustomerViewSet(viewsets.ModelViewSet):
	serializer_class = CustomerSerializer
	queryset = Customer.objects.all()

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

# assumes get parameters 
# num1 and num2
# e.g. http://127.0.0.1:8000/add?num1=5&num2=6 returns 11
def api_add(request):
	# use 1 as default
	# we should enforce type restriction by casting the value passed to a float
	# they are assumed strings by default
	# see how to cast here: https://www.w3schools.com/python/python_casting.asp
	num1 = float(request.GET.get('num1',1)) 
	num2 = float(request.GET.get('num2',1))
	added = num1 + num2 # calculate the added value
	# prepare a dict to return as a json response
  # we have to give the value 'added' a key we will call 'result'
	resp_dict = {'result':added}
	return JsonResponse(resp_dict) # return the dict as a json respone

def api_subtract(request):
	# use 1 as default
	num1 = float(request.GET.get('num1',1)) 
	num2 = float(request.GET.get('num2',1))
	subtracted = num1 - num2 # calculate the subtracted value
	# prepare a dict to return as a json response
    # we have to give the value 'added' a key we will call 'result'
	resp_dict = {'result':subtracted}
	return JsonResponse(resp_dict) # return the dict as a json respone

def api_divide(request):
	# use 1 as default
	num1 = float(request.GET.get('num1',1)) 
	num2 = float(request.GET.get('num2',1))
	divided = num1 / num2 # calculate the subtracted value
	# prepare a dict to return as a json response
    # we have to give the value 'added' a key we will call 'result'
	resp_dict = {'result':divided}
	return JsonResponse(resp_dict) # return the dict as a json respone

def api_multiply(request):
	# use 1 as default
	num1 = float(request.GET.get('num1',1)) 
	num2 = float(request.GET.get('num2',1))
	multiplied = num1 * num2 # calculate the subtracted value
	# prepare a dict to return as a json response
    # we have to give the value 'added' a key we will call 'result'
	resp_dict = {'result':multiplied}
	return JsonResponse(resp_dict) # return the dict as a json respone

def api_exponential(request):
	# use 1 as default
	num1 = float(request.GET.get('num1',1)) 
	num2 = float(request.GET.get('num2',1))
	Exponential = num1 ** num2 # calculate the subtracted value
	# prepare a dict to return as a json response
    # we have to give the value 'added' a key we will call 'result'
	resp_dict = {'result':Exponential}
	return JsonResponse(resp_dict) # return the dict as a json respone


## viewset for customers
class CustomerViewSet(viewsets.ModelViewSet):
	serializer_class = CustomerSerializer
	queryset = Customer.objects.all()

class BookViewSet(viewsets.ModelViewSet):
	serializer_class = BookSerializer
	queryset = Book.objects.all()

class BorrowedViewSet(viewsets.ModelViewSet):
	serializer_class = BorrowedSerializer
	queryset = Borrowed.objects.all()