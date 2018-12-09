# -*- coding: utf-8 -*-
# Generated by Django 1.11.9 on 2018-03-31 06:06
from __future__ import unicode_literals

import datetime
from django.db import migrations, models


class Migration(migrations.Migration):

    dependencies = [
        ('user_models', '0010_auto_20180331_0531'),
    ]

    operations = [
        migrations.AlterField(
            model_name='billingentry',
            name='billing_date',
            field=models.DateTimeField(default=datetime.datetime(2018, 3, 31, 6, 5, 59, 993157)),
        ),
        migrations.AlterField(
            model_name='complaint',
            name='complaint_date',
            field=models.DateTimeField(default=datetime.datetime(2018, 3, 31, 6, 5, 59, 993906)),
        ),
        migrations.AlterField(
            model_name='connection',
            name='created_on',
            field=models.DateTimeField(default=datetime.datetime(2018, 3, 31, 6, 5, 59, 989980)),
        ),
        migrations.AlterField(
            model_name='loginentry',
            name='auth_token',
            field=models.CharField(default=b'7b2d172c-2c3c-422e-bb34-771a10c3f04d', max_length=512),
        ),
        migrations.AlterField(
            model_name='loginentry',
            name='login_time',
            field=models.DateTimeField(default=datetime.datetime(2018, 3, 31, 6, 5, 59, 991728)),
        ),
        migrations.AlterField(
            model_name='notification',
            name='notification_date',
            field=models.DateTimeField(default=datetime.datetime(2018, 3, 31, 6, 5, 59, 994658)),
        ),
    ]
