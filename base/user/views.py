from django.contrib.auth.models import User
from django.shortcuts import render
from rest_framework import viewsets
from rest_framework import permissions

# from .models import File

from .models import Task, TaskMultiPartData, File, ExternalUrl, Text
from .serializers import TaskSerializer, UserSerializer, TaskMultiPartDataSerializer, \
    FileSerializer, ExternalUrlSerializer, TextSerializer


# Create your views here.

class TaskViewSet(viewsets.ModelViewSet):
    model = Task
    queryset = Task.objects.all()
    serializer_class = TaskSerializer

class UserViewSet(viewsets.ModelViewSet):
    model = User
    queryset = User.objects.all()
    serializer_class = UserSerializer
    permission_classes = [permissions.IsAuthenticated,]



class TaskMultiPartDataViewSet(viewsets.ModelViewSet):
    model = TaskMultiPartData
    queryset = TaskMultiPartData.objects.all()
    serializer_class = TaskMultiPartDataSerializer
    permission_classes = [permissions.IsAdminUser]

# class FileViewSet(viewsets.ModelViewSet):
#     model = File
#     queryset = File.objects.all()
#     serializer_class = FileSerializer

class ExternalUrlViewSet(viewsets.ModelViewSet):
    model = ExternalUrl
    queryset = ExternalUrl.objects.all()
    serializer_class = ExternalUrlSerializer
    permission_classes = [permissions.IsAdminUser]

class TextViewSet(viewsets.ModelViewSet):
    model = Text
    queryset = Text.objects.all()
    serializer_class = TextSerializer
    permission_classes = [permissions.IsAdminUser]

class FileViewSet(viewsets.ModelViewSet):
    model = File
    queryset = File.objects.all()
    serializer_class = FileSerializer
    permission_classes = [permissions.IsAdminUser]

