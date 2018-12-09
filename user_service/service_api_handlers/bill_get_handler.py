from datetime import datetime
from user_service.db.user_models.models import BillingEntry, LoginEntry


def get_bill_for_user(request_data):
    auth_key = request_data['AuthID']
    month=request_data['month']
    login_entry=LoginEntry.objects.get(auth_token=auth_key)
    pending_bills = BillingEntry.objects.get(user=login_entry.user,month=month)
    # last_bill = None
    # for bill in pending_bills:
    #     if bill.month.month == datetime.now().month:
    #         last_bill = pending_bills
    return pending_bills

def get_bill_for_bot(auth_key):
    login_entry=LoginEntry.objects.get(auth_token=auth_key)
    pending_bills = BillingEntry.objects.filter(user=login_entry.user).last()
    print pending_bills
    # last_bill = None
    # for bill in pending_bills:
    #     if bill.month.month == datetime.now().month:
    #         last_bill = pending_bills
    return pending_bills