package m2;

import models.Shipment;

import java.util.ArrayList;
import java.util.List;

public class BPlusDeliveryIndex {

    private List<Shipment> shipments =
            new ArrayList<>();

    public void insert(Shipment shipment) {

        shipments.add(shipment);
    }

    public void rangeQuery(
            String startDate,
            String endDate) {

        for (Shipment s : shipments) {

            if (
                s.getDeliveryDate()
                 .compareTo(startDate) >= 0

                &&

                s.getDeliveryDate()
                 .compareTo(endDate) <= 0
            ) {

                System.out.println(s);
            }
        }
    }
}
