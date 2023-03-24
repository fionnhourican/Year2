from django.db import models

# Create your models here.

class Customer(models.Model):
    id = models.AutoField(primary_key=True)
    name = models.CharField(max_length=40) # charfields have to have a max length


class Book(models.Model):
    id = models.AutoField(primary_key=True)
    title = models.TextField()
    author = models.CharField(max_length=40) # charfields have to have a max length
    genre = models.TextField(null=True)
    year = models.IntegerField()
    inventory = models.IntegerField()


class Borrowed(models.Model):
    id = models.AutoField(primary_key=True)
    book = models.ForeignKey(Book, on_delete=models.CASCADE)
    customer = models.ForeignKey(Customer, on_delete=models.CASCADE)
    is_returned = models.BooleanField()