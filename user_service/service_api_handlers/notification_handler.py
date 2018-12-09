from user_service.db.user_models.models import LoginEntry, Notification


def display_notification(request_data):
    authtoken = request_data['AuthID']
    try:
        login_object = LoginEntry.objects.get(auth_token=authtoken)
        notification_object= Notification.objects.all().filter(user=login_object.user)
        if notification_object:
            return notification_object
        else:
            return False
    except Exception as e:
        print e
        return None


