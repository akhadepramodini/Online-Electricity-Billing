from flask import request

from user_service.db.user_models.models import LoginEntry, BillingEntry


def get_history(request_data):
    authtoken = request_data['AuthID']
    month = request_data['month']
    try:
        login_object=LoginEntry.objects.filter(auth_token=authtoken).last()
        print login_object.auth_token
        billing_object=BillingEntry.objects.filter(user=login_object.user,month=month).last()
        print billing_object.month
        return billing_object
    except Exception as e:
        print e
        return None

def get_history1(request_data):
    #authtoken = request_data['AuthID']
    try:
        login_object=LoginEntry.objects.get(auth_token=request_data)
        print login_object.user
        billing_objects=BillingEntry.objects.filter(user=login_object.user)
        print  billing_objects
        return billing_objects
    except Exception as e:
        print e
        return None