# -*- coding: utf-8 -*-
# Generated by Django 1.11.15 on 2018-08-13 10:11
from __future__ import unicode_literals

import datetime
from django.db import migrations, models


class Migration(migrations.Migration):

    dependencies = [
        ('user_models', '0030_auto_20180731_0639'),
    ]

    operations = [
        migrations.AlterField(
            model_name='billingentry',
            name='billing_date',
            field=models.DateTimeField(default=datetime.datetime(2018, 8, 13, 10, 11, 10, 648247)),
        ),
        migrations.AlterField(
            model_name='complaint',
            name='complaint_date',
            field=models.DateTimeField(default=datetime.datetime(2018, 8, 13, 10, 11, 10, 649353)),
        ),
        migrations.AlterField(
            model_name='connection',
            name='created_on',
            field=models.DateTimeField(default=datetime.datetime(2018, 8, 13, 10, 11, 10, 642037)),
        ),
        migrations.AlterField(
            model_name='loginentry',
            name='auth_token',
            field=models.CharField(default=b'7c1e0b59-db04-457d-ab30-ca9f333606fc', max_length=512),
        ),
        migrations.AlterField(
            model_name='loginentry',
            name='login_time',
            field=models.DateTimeField(default=datetime.datetime(2018, 8, 13, 10, 11, 10, 646082)),
        ),
        migrations.AlterField(
            model_name='notification',
            name='notification_date',
            field=models.DateTimeField(default=datetime.datetime(2018, 8, 13, 10, 11, 10, 650585)),
        ),
    ]
