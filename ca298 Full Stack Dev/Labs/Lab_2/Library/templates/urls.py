from django.urls import path
from . import views
# from views.py import *

urlpatterns = [
    path('books', views.view_all_books, name='all_books'),
    path('books/<int:bookid>', views.view_single_book, name='single_book'),
    path('books/year/<int:bookyear>', views.view_by_year, name='year_book'),
    path('books/category/<str:bookgenre>', views.view_by_genre, name='genre_book'),
    path('books/category/<str:bookgenre>/year/<int:bookyear>', views.view_by_genre_and_year, name='genre_year_book')
]