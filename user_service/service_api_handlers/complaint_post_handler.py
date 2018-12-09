from flask import request

from user_service.db.user_models.models import Complaint, LoginEntry


def create_complaint(request_data):
    authtoken = request_data['AuthID']
    title = request_data['title']
    complaint_text = request_data['complaint_text']
    try:
        login_object = LoginEntry.objects.get(auth_token=authtoken)
        complaint_object = Complaint.objects.create(user=login_object.user,title=title,
                                                    complaint_text=complaint_text)
        return complaint_object
    except Exception as e:
        print e
        return None


def create_complaint_bot(authtoken, complaint):
    try:
        login_object = LoginEntry.objects.get(auth_token=authtoken)
        complaint_object = Complaint.objects.create(complaint_text=complaint,user=login_object.user)
        return complaint_object
    except Exception as e:
        print e
        return None

def complaint_view(request_data):
    authtoken=request_data['AuthID']
    try:
        login_object=LoginEntry.objects.get(auth_token=authtoken)
        complaint_object=Complaint.objects.all().filter(user=login_object.user)
        return complaint_object
    except Exception as e:
        print e
        return None