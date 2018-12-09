from user_service.db.user_models.models import LoginEntry
from user_service.utils.user_related_methods import \
    check_credentials_and_get_object


def create_login_entry(request_data):
    username = request_data['username']
    password = request_data['password']
    user_object = check_credentials_and_get_object(username, password)
    if user_object:
            try:
                login_entry_object, is_created = LoginEntry.objects.get_or_create(
                    user=user_object,
                    is_active=True)
                return login_entry_object
            except:
                return None

    else:
        return None

