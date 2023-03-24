from django.urls import path, include
from rest_framework import routers
from . import views

# create and define our router 
router = routers.DefaultRouter()
router.register(r'customer', views.CustomerViewSet)
router.register(r'Book', views.BookViewSet)
router.register(r'Borrowed', views.BorrowedViewSet)

urlpatterns = [
   path('', views.view_all_books, name="index"),
   path('customers', views.view_all_customers, name='all_customers'),
   path('customers/<int:customerid>', views.view_customer, name = 'customer info'),
   path('<int:bookid>', views.view_single_book, name='single_book'),
   path('category/<str:bookgenre>', views.view_by_genre, name='genre_book'),
   path('add', views.api_add, name='api_add'),
   path('subtract', views.api_subtract, name='api_subtract'),
   path('divide', views.api_divide, name='api_divide'),
   path('multiply', views.api_multiply, name='api_multiply'),
   path('exponential', views.api_exponential, name='api_exponential'),
   path('api', include(router.urls)), # add the router to our urls
]