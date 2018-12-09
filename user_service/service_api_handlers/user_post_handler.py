from user_service.db.user_models.models import User
from user_service.utils.exceptions import GenericCustomException
from user_service.utils.user_validation import \
    check_is_valid_customer_registration_get_object


def create_user(request_data):
    username = request_data['username']
    customerID = request_data['customerID']
    password = request_data['password']
    phone = request_data['phone']
    connection_object = check_is_valid_customer_registration_get_object(
        customerID)
    try:
        if connection_object:
            user_object = User.objects.create(username=username,
                                       password=password, phone=phone,connection=connection_object)
            user_object.is_confirmed=True
            user_object.save()
            print user_object.username
            return user_object
        else:
            return None

    except Exception as e:

        raise GenericCustomException(message="Error while creating user !!")
