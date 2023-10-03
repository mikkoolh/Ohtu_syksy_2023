package com.automaatio.controller.mainpage.clickActions;

import com.automaatio.model.database.Device;
import com.automaatio.utils.CacheSingleton;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;

public class DevicesClick implements ClickActions {
    private CacheSingleton cache = CacheSingleton.getInstance();
    @Override
    public void onEditClick(Device device) {
            cache.setDevice(device);
            try{
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/device.fxml"));
                Parent newView = loader.load();
                cache.getMainPane().getChildren().clear();
                cache.getMainPane().getChildren().add(newView);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    @Override
    public void onDeleteClick(Device device){

    }
    }
