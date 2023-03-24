from django.shortcuts import render, get_object_or_404
from django.http import HttpResponse
from .models import *
from .forms import *

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

def create_book(request):
    if request.method == "POST":
        # create a new copy of the form with the data the user 
		# entered , it is stored in request.POST.
        form = BookForm(request.POST)
        #
        if form.is_valid():
            book = form.save() # create the Book object and save it
            # send the user to a confirmation page saying
			# confirming that they filled in the form and the data was saved 
            return render(request, 'created.html', {'book':book})
        else:
			# form has errors
			# send the form back to the user
            return render(request, 'create_book.html', {'form': form})
    else:
        # normal get reuqest, user wants to see the form 
        form = BookForm()
        return render(request, 'create_book.html', {'form': form})

def update_book(request, bookid):
	book = get_object_or_404(Book, id=bookid) # load the book object
	if request.method=="POST":
		form = BookForm(request.POST, instance=book)
		if form.is_valid():
			form.save() # update our employee
			# redirect to a simple page confirming update was successful
			return render(request, 'created.html', {'book':book})
		else:
			
			return render(request, 'create_book.html.html', {'form':form})
	else:
		form = BookForm(instance=book)
		return render(request, 'create_book.html', {'form':form})