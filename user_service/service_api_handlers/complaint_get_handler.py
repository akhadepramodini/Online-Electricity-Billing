from user_service.db.user_models.models import BillingEntry, LoginEntry, \
    Complaint


def get_complaint(authtoken):
    try:
        login_object=LoginEntry.objects.get(auth_token=authtoken)
        if login_object:
            billing_object = BillingEntry.objects.get(user=login_object.user)

            return billing_object
    except Exception as e:
        print e
        return None

def get_complaint_for_bot(authtoken):
    try:
        login_object=LoginEntry.objects.get(auth_token=authtoken)
        if login_object:
            complaint_object = Complaint.objects.get(user=login_object.user)
            return complaint_object
    except Exception as e:
        print e
        return None

