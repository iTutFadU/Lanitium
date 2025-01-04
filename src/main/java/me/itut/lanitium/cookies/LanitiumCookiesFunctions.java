package me.itut.lanitium.cookies;

import carpet.script.CarpetContext;
import carpet.script.CarpetScriptServer;
import carpet.script.Context;
import carpet.script.annotation.ScarpetFunction;
import carpet.script.value.*;
import net.minecraft.nbt.Tag;
import net.minecraft.server.level.ServerPlayer;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class LanitiumCookiesFunctions {
    @ScarpetFunction
    public LanitiumCookieFuture cookie(Context c, EntityValue p, FunctionValue callback) {
        CarpetContext context = (CarpetContext)c;
        ServerPlayer player = EntityValue.getPlayerByValue(context.server(), p);
        CarpetScriptServer server = ((CarpetScriptServer)context.host.scriptServer());
        return new LanitiumCookieFuture(player.getCookie(LanitiumCookie.class).whenComplete((cookie, exception) -> {
            MapValue map = null;
            boolean set = false;

            if (cookie != null) {
                Map<Value, Value> values = new HashMap<>();
                for (Map.Entry<String, Tag> e : cookie.cookie.entrySet())
                    values.put(StringValue.of(e.getKey()), NBTSerializableValue.of(e.getValue()));
                map = MapValue.wrap(values);
            }

            try {
                List<Value> args = new ArrayList<>();
                args.add(p);
                args.add(map);
                Value out = server.getAppHostByName(context.host.getName()).callNow(callback, args);
                if ("set".equals(out.getString())) set = true;
            } finally {
                if (set)
                    if (map == null)
                        player.setCookie(LanitiumCookie.EMPTY);
                    else {
                        Map<String, Tag> newCookie = new HashMap<>();
                        for (Map.Entry<Value, Value> e : map.getMap().entrySet())
                            newCookie.put(e.getKey().getString(), ((NBTSerializableValue)NBTSerializableValue.fromValue(e.getValue())).getTag());
                        player.setCookie(new LanitiumCookie(newCookie));
                    }
            }
        }));
    }

    @ScarpetFunction
    public void cookie_reset(Context c, EntityValue p) {
        CarpetContext context = (CarpetContext)c;
        ServerPlayer player = EntityValue.getPlayerByValue(context.server(), p);
        player.setCookie(LanitiumCookie.EMPTY);
    }

    @ScarpetFunction
    public void cookie_secret(String secret) {
        LanitiumCookies.COOKIE.setSecret(secret);
    }
}
