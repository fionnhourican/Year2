from django.urls import path
from . import views

urlpatterns = [
   path('', views.view_all_books, name="index"),
   path('customers', views.view_all_customers, name='all_customers'),
   path('customers/<int:customerid>', views.view_customer, name = 'customer info'),
   path('<int:bookid>', views.view_single_book, name='single_book'),
   path('category/<str:bookgenre>', views.view_by_genre, name='genre_book'),
]