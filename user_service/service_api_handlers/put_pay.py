from user_service.db.user_models.models import BillingEntry,LoginEntry


def pay(pay_object):
    try:
        auth_token=pay_object['AuthID']
        month=pay_object['month']
        login_object=LoginEntry.objects.get(auth_token=auth_token)
        payble_object = BillingEntry.objects.get(user=login_object.user,month=month)
        payble_object.is_paid=True;
        payble_object.save()
        return payble_object
    except Exception as e:
        print e
        return None
