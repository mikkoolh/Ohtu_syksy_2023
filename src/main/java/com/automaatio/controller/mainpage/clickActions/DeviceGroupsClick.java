package com.automaatio.controller.mainpage.clickActions;

import com.automaatio.model.database.Device;
import com.automaatio.model.database.DeviceGroup;
import com.automaatio.utils.CacheSingleton;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;

public class DeviceGroupsClick implements ClickActions {
    private CacheSingleton cache = CacheSingleton.getInstance();
    @Override
    public void onEditClick(Object object) {
        cache.setRoom((DeviceGroup) object);
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/room.fxml"));
            Parent newView = loader.load();
            cache.getMainPane().getChildren().clear();
            cache.getMainPane().getChildren().add(newView);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onDeleteClick(Device device) {

    }
}
