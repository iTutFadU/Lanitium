[<img alt="modrinth" height="56" src="https://cdn.jsdelivr.net/npm/@intergrav/devins-badges@3/assets/cozy/available/modrinth_vector.svg">](https://modrinth.com/lanitium-cookies)
[<img alt="github" height="56" src="https://cdn.jsdelivr.net/npm/@intergrav/devins-badges@3/assets/cozy/available/github_vector.svg">](https://github.com/iTutFadU/lanitium-cookies)

# Lanitium Cookies

**A simple Carpet extension for manipulating 1.20.5 cookies**

Uses [Biscuit!](https://modrinth.com/mod/biscuit!)

# Functions

### `cookie(player, callback)`
Tries to get the cookie (`lanitium:cookie`) from the player. Returns a `lanitium_cookie_future`, see below.
- `player` - Player for which to get the cookie
- `callback(player, cookie)` - The function to call when the cookie is got
    - `cookie` - The cookie. A map from `string` to `nbt`

### `cookie_reset(player)`
Resets the player's cookie to an empty map.

### `cookie_secret(secret)`
Sets the cookie secret. Used for validation, apparently.
- `secret` - The secret string

# Types

## `lanitium_cookie_future`

### `future ~ 'done'`
Returns wether the future is done. `true` if the callback finished or the future has been cancelled (if there's no cookie), `false` otherwise.

### `future ~ 'cancelled'`
Returns wether the future has been cancelled. `true` if there was an error (i.e. there's no cookie), `false` otherwise.
