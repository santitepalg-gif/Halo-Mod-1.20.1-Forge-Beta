# Halo Mobs (Forge 1.20.1)

Mobs del Covenant para Minecraft Java 1.20.1 (Forge 47.x) usando [GeckoLib 4](https://github.com/bernie-ecosystem/GeckoLib), portados desde assets de un addon Bedrock.

## Mobs
| Mob | ID | Animaciones portadas |
|---|---|---|
| Elite | `halomobs:elite` | idle, caminar, correr, ataque, muerte |
| Grunt | `halomobs:grunt` | idle, caminar, correr, melee, muerte |
| Jackal | `halomobs:jackal` | idle, caminar, correr, ataque, muerte |
| Hunter | `halomobs:hunter` | ninguna (no había archivo de animación) |
| Brute Capitán | `halomobs:brute_capitan` | idle, caminar, correr, ataque, muerte |
| Brute Cacique | `halomobs:brute_cacique` | idle, correr, ataque, muerte |

Cada mob tiene huevo generador en la pestaña creativa **Halo Mobs**. Son hostiles hacia jugadores.

## Estado actual: modelos y texturas PLACEHOLDER
Los archivos recibidos eran solo animaciones y definiciones de entidad; **faltaban los modelos (`.geo.json`) y las texturas (`.png`)**.
Por eso cada mob usa un humanoide de cubos con textura de color liso, solo para que las animaciones funcionen.
Para ponerles su aspecto real, reemplaza (mismo nombre):

- `src/main/resources/assets/halomobs/geo/entity/<id>.geo.json`
- `src/main/resources/assets/halomobs/textures/entity/<id>.png`

Los huesos del modelo deben llamarse igual que en las animaciones (`all`, `head`, `body`, `rightArm`, `leftArm`, `rightleg`, `leftleg`, `manoderecha`, `rodilladerecha`, `Brazoderecho`, `Martillo`, etc.). Los huesos que no existan se ignoran.

## Pendiente / no portado
- Sonidos (`mob.halo_*`) y armas/escudos (attachables) del addon original.
- Animaciones de armas, granada, disparo y bloqueo existen en los JSON pero la IA actual no las usa (solo melee).
- Spawn natural: por ahora solo con huevos o `/summon halomobs:elite`.
- `brute2` y `Elite_animation.json` (`guns`, tenía valores `NaN`) no se incluyeron.

## Compilar
Requiere Java 17.
```
gradle build      # o ./gradlew build si generas el wrapper con: gradle wrapper --gradle-version 8.1.1
```
El .jar queda en `build/libs/`. Para probar: `gradle runClient`.
GitHub Actions (`.github/workflows/build.yml`) compila en cada push y sube el .jar como artefacto.

## Licencia
Halo es propiedad de Microsoft/343 Industries. Proyecto fan sin fines de lucro; revisa los derechos de los assets originales antes de publicar.
