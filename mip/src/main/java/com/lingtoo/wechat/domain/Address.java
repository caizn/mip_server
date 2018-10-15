package com.lingtoo.wechat.domain;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created: 2015/12/29.
 * Author: Qiannan Lu
 */
public class Address {
    private Location location;

    private AddressComponent addressComponent;

    @JsonProperty("formatted_address")
    private String formattedAddress;

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public AddressComponent getAddressComponent() {
        return addressComponent;
    }

    public void setAddressComponent(AddressComponent addressComponent) {
        this.addressComponent = addressComponent;
    }

    public String getFormattedAddress() {
        return formattedAddress;
    }

    public void setFormattedAddress(String formattedAddress) {
        this.formattedAddress = formattedAddress;
    }
}
