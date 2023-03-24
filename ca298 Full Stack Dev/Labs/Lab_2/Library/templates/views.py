from django.shortcuts import render, get_object_or_404
from .models import *

# Create your views here.

def view_all_books(request):
    all_books = Book.objects.all() # obtain a list of all books
    return render(request, 'all_books.html', {'books':all_books}) # pass this list to a webpage

def view_single_book(request, bookid):
	single_book = get_object_or_404(Book, id=bookid)
	return render(request, 'single_book.html', {'book':single_book})

def view_by_year(request, bookyear):
    year_book = Book.objects.all().filter(year=bookyear)
    return render(request, 'year.html', {'book':year_book})

def view_by_genre(request, bookgenre):
    genre_book = Book.objects.all().filter(genre=bookgenre)
    return render(request, 'year.html', {'book':genre_book})

def view_by_genre_and_year(request, bookgenre, bookyear):
    genre_year_book = Book.objects.all().filter(genre=bookgenre, year=bookyear)
    return render(request, 'year.html', {'book':genre_year_book})