package com.orderp.OrdERP.application.controller.health_check;

import com.orderp.OrdERP.AppplicationTestCase;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class HealthCheckGetControllerShould extends AppplicationTestCase {

    @Test
    void check_status_app_is_ok() throws Exception{
        assertResponse("/health-check", 200, "{'application':'orderp','status':'ok'}");
    }
}