# -*- coding: utf-8 -*-
# Generated by Django 1.11.9 on 2018-03-31 01:24
from __future__ import unicode_literals

import datetime
from django.db import migrations, models
import django.db.models.deletion


class Migration(migrations.Migration):

    initial = True

    dependencies = [
    ]

    operations = [
        migrations.CreateModel(
            name='BillingEntry',
            fields=[
                ('id', models.AutoField(auto_created=True, primary_key=True, serialize=False, verbose_name='ID')),
                ('month', models.CharField(max_length=20)),
                ('last_date', models.CharField(max_length=20)),
                ('billing_units', models.IntegerField()),
                ('bill_amount', models.IntegerField()),
                ('is_paid', models.BooleanField(default=False)),
                ('billing_date', models.DateTimeField(default=datetime.datetime(2018, 3, 31, 1, 24, 22, 947087))),
            ],
        ),
        migrations.CreateModel(
            name='Complaint',
            fields=[
                ('id', models.AutoField(auto_created=True, primary_key=True, serialize=False, verbose_name='ID')),
                ('title', models.CharField(max_length=20, null=True)),
                ('complaint_text', models.CharField(max_length=1024)),
                ('status', models.CharField(choices=[(b'Viewed', b'Viewed'), (b'Processing', b'Processing'), (b'Done', b'Done')], max_length=124, null=True)),
                ('complaint_date', models.DateTimeField(default=datetime.datetime(2018, 3, 31, 1, 24, 22, 947716))),
            ],
        ),
        migrations.CreateModel(
            name='Connection',
            fields=[
                ('id', models.AutoField(auto_created=True, primary_key=True, serialize=False, verbose_name='ID')),
                ('customer_id', models.CharField(max_length=15)),
                ('name', models.CharField(max_length=256)),
                ('email_id', models.CharField(max_length=50)),
                ('survey_number', models.CharField(max_length=254)),
                ('society_name', models.CharField(max_length=256)),
                ('village', models.CharField(max_length=256, null=True)),
                ('taluka', models.CharField(max_length=256, null=True)),
                ('district', models.CharField(max_length=254, null=True)),
                ('pincode', models.IntegerField()),
                ('supply_type', models.CharField(choices=[(b'Single_phase', b'Single_phase'), (b'Three_phase', b'Three_phase'), (b'HT_supply', b'HT_supply')], default=None, max_length=124)),
                ('consumer_type', models.CharField(choices=[(b'LT', b'LT'), (b'HT', b'HT'), (b'EHV', b'EHV')], default=None, max_length=124)),
                ('created_on', models.DateTimeField(default=datetime.datetime(2018, 3, 31, 1, 24, 22, 944106))),
                ('is_valid', models.BooleanField(default=False)),
            ],
        ),
        migrations.CreateModel(
            name='LoginEntry',
            fields=[
                ('id', models.AutoField(auto_created=True, primary_key=True, serialize=False, verbose_name='ID')),
                ('login_time', models.DateTimeField(default=datetime.datetime(2018, 3, 31, 1, 24, 22, 945719))),
                ('auth_token', models.CharField(default=b'07b8ac7e-c47d-4bd2-b7a7-498c9c412787', max_length=512)),
                ('is_active', models.BooleanField(default=True)),
            ],
        ),
        migrations.CreateModel(
            name='Notification',
            fields=[
                ('id', models.AutoField(auto_created=True, primary_key=True, serialize=False, verbose_name='ID')),
                ('notification_text', models.CharField(max_length=240)),
                ('notification_date', models.DateTimeField(default=datetime.datetime(2018, 3, 31, 1, 24, 22, 948388))),
            ],
        ),
        migrations.CreateModel(
            name='OTP',
            fields=[
                ('id', models.AutoField(auto_created=True, primary_key=True, serialize=False, verbose_name='ID')),
                ('otp', models.IntegerField()),
                ('is_valid', models.BooleanField(default=True)),
            ],
        ),
        migrations.CreateModel(
            name='Tracking',
            fields=[
                ('id', models.AutoField(auto_created=True, primary_key=True, serialize=False, verbose_name='ID')),
                ('tracking_id', models.CharField(max_length=30)),
                ('message', models.CharField(max_length=1024, null=True)),
                ('track_status', models.CharField(choices=[(b'Initiated', b'Initiated'), (b'Pending', b'Pending'), (b'Accepted', b'Accepted'), (b'Rejected', b'Rejected')], default=None, max_length=124)),
            ],
        ),
        migrations.CreateModel(
            name='User',
            fields=[
                ('username', models.CharField(max_length=25, primary_key=True, serialize=False)),
                ('password', models.CharField(max_length=256)),
                ('phone', models.CharField(max_length=20, null=True)),
                ('is_confirmed', models.BooleanField(default=False)),
                ('connection', models.ForeignKey(on_delete=django.db.models.deletion.CASCADE, to='user_models.Connection')),
            ],
        ),
        migrations.AddField(
            model_name='otp',
            name='user',
            field=models.ForeignKey(on_delete=django.db.models.deletion.CASCADE, to='user_models.User'),
        ),
        migrations.AddField(
            model_name='notification',
            name='user',
            field=models.ForeignKey(on_delete=django.db.models.deletion.CASCADE, to='user_models.User'),
        ),
        migrations.AddField(
            model_name='loginentry',
            name='user',
            field=models.ForeignKey(on_delete=django.db.models.deletion.CASCADE, to='user_models.User'),
        ),
        migrations.AddField(
            model_name='connection',
            name='tracking',
            field=models.ForeignKey(on_delete=django.db.models.deletion.CASCADE, to='user_models.Tracking'),
        ),
        migrations.AddField(
            model_name='complaint',
            name='user',
            field=models.ForeignKey(on_delete=django.db.models.deletion.CASCADE, to='user_models.User'),
        ),
        migrations.AddField(
            model_name='billingentry',
            name='user',
            field=models.ForeignKey(on_delete=django.db.models.deletion.CASCADE, to='user_models.User'),
        ),
    ]