# serializers.py created inside your app folder
from rest_framework import serializers
from .models import *

class CustomerSerializer(serializers.HyperlinkedModelSerializer):
	class Meta:
		model = Customer
		fields = ['id','name']#


class BookSerializer(serializers.HyperlinkedModelSerializer):
	class Meta:
		model = Book
		fields = ['id','title', 'author', 'genre', 'year', 'inventory']


class BorrowedSerializer(serializers.HyperlinkedModelSerializer):
	class Meta:
		model = Borrowed
		fields = ['id','book', 'customer', 'is_returned']