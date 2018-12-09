# -*- coding: utf-8 -*-
# Generated by Django 1.11.9 on 2018-03-31 06:12
from __future__ import unicode_literals

import datetime
from django.db import migrations, models


class Migration(migrations.Migration):

    dependencies = [
        ('user_models', '0012_auto_20180331_0606'),
    ]

    operations = [
        migrations.AlterField(
            model_name='billingentry',
            name='billing_date',
            field=models.DateTimeField(default=datetime.datetime(2018, 3, 31, 6, 12, 53, 360392)),
        ),
        migrations.AlterField(
            model_name='complaint',
            name='complaint_date',
            field=models.DateTimeField(default=datetime.datetime(2018, 3, 31, 6, 12, 53, 362010)),
        ),
        migrations.AlterField(
            model_name='connection',
            name='created_on',
            field=models.DateTimeField(default=datetime.datetime(2018, 3, 31, 6, 12, 53, 353677)),
        ),
        migrations.AlterField(
            model_name='loginentry',
            name='auth_token',
            field=models.CharField(default=b'e8b6ef9d-3238-4134-814b-45e9072f4a66', max_length=512),
        ),
        migrations.AlterField(
            model_name='loginentry',
            name='login_time',
            field=models.DateTimeField(default=datetime.datetime(2018, 3, 31, 6, 12, 53, 357322)),
        ),
        migrations.AlterField(
            model_name='notification',
            name='notification_date',
            field=models.DateTimeField(default=datetime.datetime(2018, 3, 31, 6, 12, 53, 363677)),
        ),
    ]