# Generated by Django 4.1.5 on 2023-02-03 11:05

from django.db import migrations


class Migration(migrations.Migration):

    dependencies = [
        ('Library', '0002_borrowed_due_date'),
    ]

    operations = [
        migrations.RemoveField(
            model_name='borrowed',
            name='due_date',
        ),
    ]
