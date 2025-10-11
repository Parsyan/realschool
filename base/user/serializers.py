from django.contrib.auth.models import User
from rest_framework import serializers

from .models import File, Task, TaskMultiPartData, Text, ExternalUrl


class FileSerializer(serializers.ModelSerializer):
    class Meta:
        model = File
        fields = ['id', 'file',]


class TaskSerializer(serializers.ModelSerializer):
    class Meta:
        model = Task
        fields = ['title', 'query', 'solution', 'solved', 'solvers', 'creator', 'confirmer', 'pub_date']


class UserSerializer(serializers.ModelSerializer):
    class Meta:
        model = User
        fields = ['username', 'first_name', 'last_name', 'email']

class TaskMultiPartDataSerializer(serializers.ModelSerializer):
    class Meta:
        model = TaskMultiPartData
        fields = ['text', 'url', 'file']

class TextSerializer(serializers.ModelSerializer):
    class Meta:
        model = Text
        fields = ['text']

# class FileSerializer(serializers.ModelSerializer):
#     class Meta:
#         model = File
#         fields = ['file']

# class FileSerializer(serializers.ModelSerializer):
#     class Meta:
#         model = File
#         fields = ['id', 'filename', 'file']


class ExternalUrlSerializer(serializers.ModelSerializer):
    class Meta:
        model = ExternalUrl
        fields = ['url']