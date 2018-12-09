from user_service.db.user_models.models import Tracking, Connection
from user_service.utils.send_customerid_methods import send_customerid_email


def update_tracking(request_data):
    tracking_id=request_data['id']
    message=request_data['message']
    status=request_data['status']

    update_object=Tracking.objects.get(id=tracking_id)
    update_object.message=message
    update_object.track_status=status
    update_object.save()
    if update_object.track_status == "Accepted":
        connection_object=Connection.objects.get(tracking=update_object)
        connection_object.customer_id = "SSEB" + str(10000 + connection_object.id)
        connection_object.save()
        send_customerid_email(connection_object)
    return update_object

def put_tracking_handler(request_data):
    tracking_id=request_data['tracking_id']
    conection_object=Connection.objects.get(customer_id=tracking_id)

    tracking_object=Tracking.objects.filter(id=conection_object.tracking_id).last()
    print tracking_object.id
    return tracking_object