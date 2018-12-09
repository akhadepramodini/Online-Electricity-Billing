
from user_service.utils.sms_utils import send_sms
from random import *
def create_OTP(request_data):
    phone=request_data['phone']
    object = str(randint(1000, 9999))
    sms_utlis.send_sms(phone,object)
    return True