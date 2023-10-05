package com.automaatio.controller.mainpage.clickActions;

import com.automaatio.model.database.Device;

public interface ClickActions {
    void onEditClick(Object object);
    void onDeleteClick(Device device);
}
