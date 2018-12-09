from user_service.conf.environment.config import Config
from user_service.db.user_models.models import LoginEntry
from user_service.pdf import pdf_generation
from user_service.service_api_handlers import bill_get_handler, \
    complaint_get_handler, complaint_post_handler


def get_my_bill(auth_token, query=None):
    pending_bill = bill_get_handler.get_bill_for_bot(
        auth_token)
    print pending_bill.month
     # return jsonify({"bill":get_dict_for_bill_object(pending_bill)})
    if pending_bill:
        filename = pdf_generation.send_pdf(pending_bill)
        return str(filename),'view_pdf'

def complaint_status(auth_token):
    response = complaint_get_handler.get_complaint_for_bot(auth_token)
    print response.user
    return str(str(response.user.username)+" "+str(response.title)+" "+str(response.complaint_text)+" "+str(response.status)), 'no_action'

def bill_History(auth_token):
    pending_bill = bill_get_handler.get_bill_for_bot(auth_token)
    if pending_bill:
        print pending_bill.month
        return str(str(pending_bill.user.username)+" "+str(pending_bill.month)+" "+str(pending_bill.last_date)+" "+str(pending_bill.billing_units)+" "+str(pending_bill.bill_amount)+" "+str(pending_bill.is_paid)),'no_action'

def   bill_History1(auth_token):
    pending_bill = bill_get_handler.get_bill_for_bot(auth_token)
    if pending_bill:
        return str(pending_bill.last_date),'no_action'

def complaint_registration_for_bot(auth_token, query=None):

    complaint_post_handler.create_complaint_bot(auth_token, query)
    return 'Your complaint has been registered succesfully you can check it in history','no_action'

def screenshot_app_register(auth_token, query=None):
    login_object = LoginEntry.objects.get(
        auth_token=auth_token)
    if login_object:
        return 'appworking.pdf','view_pdf'

def screenshot_bill_complaint(auth_token, query=None):
    login_object = LoginEntry.objects.get(
        auth_token=auth_token)
    if login_object:
        return 'complaints.pdf','view_pdf'

def complaint_registration(auth_token, query=None):
    login_object = LoginEntry.objects.get(
        auth_token=auth_token)
    if login_object:
        return 'you can go in complaints module and register your complaint or you can tell me your problem here itself','no_action'

def unable_to_response(auth_token,query=None):
    login_object=LoginEntry.objects.get(auth_token=auth_token)
    if login_object:
        return 'Sorry! I did not understand will get back to you later with a response.','no_action'