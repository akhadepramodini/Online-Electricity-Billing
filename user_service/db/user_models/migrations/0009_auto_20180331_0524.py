# -*- coding: utf-8 -*-
# Generated by Django 1.11.9 on 2018-03-31 05:24
from __future__ import unicode_literals

import datetime
from django.db import migrations, models


class Migration(migrations.Migration):

    dependencies = [
        ('user_models', '0008_auto_20180331_0501'),
    ]

    operations = [
        migrations.AlterField(
            model_name='billingentry',
            name='billing_date',
            field=models.DateTimeField(default=datetime.datetime(2018, 3, 31, 5, 24, 37, 247319)),
        ),
        migrations.AlterField(
            model_name='complaint',
            name='complaint_date',
            field=models.DateTimeField(default=datetime.datetime(2018, 3, 31, 5, 24, 37, 247952)),
        ),
        migrations.AlterField(
            model_name='complaint',
            name='status',
            field=models.CharField(choices=[(b'Viewed', b'Viewed'), (b'Processing', b'Processing'), (b'Done', b'Done')], default=b'Viewed', max_length=124),
        ),
        migrations.AlterField(
            model_name='connection',
            name='created_on',
            field=models.DateTimeField(default=datetime.datetime(2018, 3, 31, 5, 24, 37, 244412)),
        ),
        migrations.AlterField(
            model_name='loginentry',
            name='auth_token',
            field=models.CharField(default=b'0ece0b73-9c67-4c77-9c1d-6cacd6a581f3', max_length=512),
        ),
        migrations.AlterField(
            model_name='loginentry',
            name='login_time',
            field=models.DateTimeField(default=datetime.datetime(2018, 3, 31, 5, 24, 37, 246026)),
        ),
        migrations.AlterField(
            model_name='notification',
            name='notification_date',
            field=models.DateTimeField(default=datetime.datetime(2018, 3, 31, 5, 24, 37, 248621)),
        ),
    ]
