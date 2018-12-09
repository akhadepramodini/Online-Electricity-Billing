
from django.core.mail import send_mail


def send_customerid_email(connection_object):
    print connection_object.email_id
    email_id=connection_object.email_id
    customer_id=connection_object.customer_id
    message="This is your customerID :-"+str(customer_id)
    send_mail("welcome to Electricity app", message, 'kadamshubham66@gmail.com',[email_id], html_message=None)
    return "success..."

def send_tracking_id(connection_object):
    print connection_object.email_id
    email_id=connection_object.email_id
    message="This is your trackingID :-"+str(connection_object.tracking.id)
    send_mail("welcome to Electricity app", message, 'kadamshubham66@gmail.com',[email_id], html_message=None)
    return "success..."

def send_pending(connection_object):
    print connection_object.email_id
    email_id=connection_object.email_id
    message="Your Documents are Pending....."
    send_mail("welcome to Electricity app", message, 'kadamshubham66@gmail.com',[email_id], html_message=None)
    return "success..."
