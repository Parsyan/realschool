from django.urls import path, include
from rest_framework.routers import DefaultRouter

from . import views
# from .views import FileViewSet

from .views import TaskViewSet, UserViewSet, TaskMultiPartDataViewSet, FileViewSet, ExternalUrlViewSet, TextViewSet

router = DefaultRouter()
router.register(r'files', views.FileViewSet)
router.register(r'tasks', TaskViewSet)
router.register(r'users', UserViewSet)
router.register(r'multipartData', TaskMultiPartDataViewSet)
# router.register(r'files', FileViewSet)
router.register(r'urls', ExternalUrlViewSet)
router.register(r'texts', TextViewSet)

urlpatterns = [
    path('', include(router.urls))
]