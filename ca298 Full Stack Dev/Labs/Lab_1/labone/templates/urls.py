from django.urls import path
from . import views

urlpatterns = [
   path('', views.index, name="index"), # 127.0.0.1:8000/index  // homepage
   path('contact', views.contact, name="contact") # 127.0.0.1:8000/contact  // contact page
]