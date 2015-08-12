package com.cendrb.rododendron.item;

import com.cendrb.rododendron.item.charger.ItemChargerFuel;
import com.cendrb.rododendron.reference.Names;

/**
 * Created by cendr_000 on 3. 7. 2015.
 */
public class ItemGaySemen extends ItemChargerFuel {

    public ItemGaySemen()
    {
        super();
        setUnlocalizedName(Names.Items.gaySemen);
    }

    @Override
    public int getChargerFuelValue() {
        return 200;
    }
}
