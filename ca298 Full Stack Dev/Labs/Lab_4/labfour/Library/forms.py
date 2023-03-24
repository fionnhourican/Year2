#imports for forms.py 
from django import forms
from .models import * # import all of your models
from datetime import datetime

class BookForm(forms.ModelForm):
    class Meta:
        model = Book # picked up from importing models.py
        fields = ['title','author','genre', 'year', 'inventory']
    
    def clean(self):
        # all fields are stored in self.cleaned data 
		# this is a dictionary containing the fields we defined in the "Meta" class
        # and the values the user entered
        data = self.cleaned_data
        title = data['title']
        year = data['year']

        current_datetime = datetime.now()
        current_year = current_datetime.year

        title_exists = Book.objects.filter(title=title).exists()
        book_year = Book.objects.filter(year=year)

        if title_exists:
            # if there is an error , raise it as a validation error
            raise forms.ValidationError("A book of that title already exists")
        elif year > current_year:
            raise forms.ValidationError("A book cannot be written in the future")

        return data # always return self.cleaned_data at the end of clean
