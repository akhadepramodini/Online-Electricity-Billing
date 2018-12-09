from user_service.db.user_models.models import Connection, Tracking
from user_service.utils.exceptions import GenericCustomException
import random

def create_connection(request_data):
    consumer_type = request_data['consumerType']
    supply_type = request_data['supplyType']
    name = request_data['name']
    email_id = request_data['emailId']
    survey_number = request_data['surveyNumber']
    society_name = request_data['societyName']
    village = request_data['village']
    taluka = request_data['taluka']
    district = request_data['district']
    pin_code = request_data['pincode']
    try:
        tracking_object=Tracking.objects.create(message="request accepted we will notify you afterwords")
        connection_object = Connection.objects.create(
            consumer_type=consumer_type, supply_type=supply_type,
            name = name,email_id=email_id,
            survey_number=survey_number, society_name=society_name,
            village=village,taluka=taluka,
            district=district,pincode=pin_code,tracking=tracking_object)
        x = random.randint(1001, 100000)
        print x
        connection_object.customer_id = x
        connection_object.save()
        print connection_object.customer_id
        return connection_object
    except Exception as e:
        print e
        #raise GenericCustomException(message="Error while creating connection !!")
        return None



