# -*- coding: utf-8 -*-
# Generated by Django 1.11.15 on 2018-08-13 10:55
from __future__ import unicode_literals

import datetime
from django.db import migrations, models


class Migration(migrations.Migration):

    dependencies = [
        ('user_models', '0031_auto_20180813_1011'),
    ]

    operations = [
        migrations.AlterField(
            model_name='billingentry',
            name='billing_date',
            field=models.DateTimeField(default=datetime.datetime(2018, 8, 13, 10, 55, 45, 13768)),
        ),
        migrations.AlterField(
            model_name='complaint',
            name='complaint_date',
            field=models.DateTimeField(default=datetime.datetime(2018, 8, 13, 10, 55, 45, 14853)),
        ),
        migrations.AlterField(
            model_name='connection',
            name='created_on',
            field=models.DateTimeField(default=datetime.datetime(2018, 8, 13, 10, 55, 45, 7772)),
        ),
        migrations.AlterField(
            model_name='loginentry',
            name='auth_token',
            field=models.CharField(default=b'6d16ce66-5eaa-4f17-9af5-6db5ff557c4a', max_length=512),
        ),
        migrations.AlterField(
            model_name='loginentry',
            name='login_time',
            field=models.DateTimeField(default=datetime.datetime(2018, 8, 13, 10, 55, 45, 11669)),
        ),
        migrations.AlterField(
            model_name='notification',
            name='notification_date',
            field=models.DateTimeField(default=datetime.datetime(2018, 8, 13, 10, 55, 45, 16133)),
        ),
    ]