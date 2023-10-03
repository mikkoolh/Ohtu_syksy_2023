package com.automaatio.controller.mainpage;

import com.automaatio.model.database.Device;

public interface ClickActions {
    void onEditClick(Device device);
    void onDeleteClick(Device device);
}
