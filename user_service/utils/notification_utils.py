from user_service.utils.user_related_methods import get_user_dict_response


def get_notifications(notification_object):
    response_dict = {
        "Date": notification_object.notification_date,
        "Notification_text": notification_object.notification_text
    }

    return response_dict