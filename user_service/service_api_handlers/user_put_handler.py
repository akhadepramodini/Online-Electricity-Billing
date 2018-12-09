def update_user(user_object, data):
    if "phone" in data:
        user_object.phone = data['phone']

    user_object.save()
    return user_object
