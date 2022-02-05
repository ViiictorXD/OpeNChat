package com.vtr.openchat.service.mute;

import com.vtr.openchat.model.Mute;
import com.vtr.openchat.service.Service;

/**
 * This file is part of a ViiictorXD project
 * <p>
 * Copyright (c) ViiictorXD
 * https://github.com/viiictorxd
 **/
public interface MuteService extends Service {

    Mute match(String violator);

    void constructor(Mute mute);

    void destructor(String violator);
}
