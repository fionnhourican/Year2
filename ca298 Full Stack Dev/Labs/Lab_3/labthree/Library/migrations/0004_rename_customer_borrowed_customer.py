# Generated by Django 4.1.5 on 2023-02-03 11:58

from django.db import migrations


class Migration(migrations.Migration):

    dependencies = [
        ('Library', '0003_remove_borrowed_due_date'),
    ]

    operations = [
        migrations.RenameField(
            model_name='borrowed',
            old_name='Customer',
            new_name='customer',
        ),
    ]