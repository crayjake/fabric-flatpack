# Flatpack - A superflat mod that piggybacks vanilla generation
## Setup
Download the latest \*.jar (not \*\_sources.jar)

## Features
At the moment the overworld is the only place that is flat. I am unsure what would be good for the nether and end dimensions. This is still very early on in development so there may be crashes/bugs and definitely some jank (arguably less so than vanilla superflat...).

### Working features 😍
Shipwrecks and geodes also generate correctly (pretty much everything, except for the janky examples below). Also the mansion is kinda janky, but it's completely above ground.
| Feature | Image |
| ------- | ----- |
| village |  ![village](https://github.com/crayjake/flatpack/blob/d502af3e6b934651d85f7a74d185eb2ee284cbed/images/village.png) |
| desert_village | ![desert_village](https://github.com/crayjake/flatpack/blob/b506174efd10f0ca3ebe531bb83642b92221c754/images/desert_village.png) |
| igloo | ![igloo](https://github.com/crayjake/flatpack/blob/b506174efd10f0ca3ebe531bb83642b92221c754/images/igloo.png) |
| jungle_temple | ![jungle_temple](https://github.com/crayjake/flatpack/blob/b506174efd10f0ca3ebe531bb83642b92221c754/images/jungle_temple.png) |
| mansion | ![mansion](https://github.com/crayjake/flatpack/blob/b506174efd10f0ca3ebe531bb83642b92221c754/images/mansion.png) |
| pillager_outpost | ![pillager_outpost](https://github.com/crayjake/flatpack/blob/b506174efd10f0ca3ebe531bb83642b92221c754/images/pillager.png) |
| ruined_portal | ![ruined_portal](https://github.com/crayjake/flatpack/blob/b506174efd10f0ca3ebe531bb83642b92221c754/images/ruined_portal.png) |
| swamp_hut | ![swamp_hut](https://github.com/crayjake/flatpack/blob/b506174efd10f0ca3ebe531bb83642b92221c754/images/swamp_hut.png) |
| stronghold | ![stronghold](https://github.com/crayjake/flatpack/blob/b506174efd10f0ca3ebe531bb83642b92221c754/images/stronghold2.png) |

### Janky features 🥴
| Jank | Description | Image | 
| ---- | ----------- | ----- | 
| desert_temple | Spawns underground, with spiral like feature above ground (pretty cool...) | ![desert_temple_above](https://github.com/crayjake/flatpack/blob/b506174efd10f0ca3ebe531bb83642b92221c754/images/janky_desert_pyramid1.png) ![desert_temple_below](https://github.com/crayjake/flatpack/blob/b506174efd10f0ca3ebe531bb83642b92221c754/images/janky_desert_pyramid2.png) |
| village | Some houses spawn incorrectly but still better than vanilla eh? | ![village](https://github.com/crayjake/flatpack/blob/b506174efd10f0ca3ebe531bb83642b92221c754/images/janky_village.png) |
| trees | Especially above jungles this is common (explained in bugs below) | ![tree](https://github.com/crayjake/flatpack/blob/b506174efd10f0ca3ebe531bb83642b92221c754/images/janky_tree.png) |

### Cool artifact 😎
Interestingly my generation leaves behind bedrock caves (i.e. caves made out of bedrock) below the bedrock layer (I believe there may be entrances without needing to break bedrock). These will not affect performance as mobs will not be able to spawn on bedrock, but they look awesome (and are spawnproof).
![bedrock_caves](https://github.com/crayjake/flatpack/blob/b506174efd10f0ca3ebe531bb83642b92221c754/images/bedrock_caves.png)

## Known bugs 🐞
 - There was a lighting bug, which I believe is now fixed (let me know if you get any crashes: NullPointerException something to do with chunk sections and nibbles).
 - The janky villages/desert_temples/trees all stems from a generation problem. This mod is implemented using configured features. This gives us control (chunk by chunk) at any given stage in the generation process (top - bottom : first - last):
     - RAW_GENERATION
     - LAKES
     - LOCAL_MODIFICATIONS
     - UNDERGROUND_STRUCTURES
     - SURFACE_STRUCTURES
     - STRONGHOLDS
     - UNDERGROUND_ORES
     - UNDERGROUND_DECORATION
     - FLUID_SPRINGS
     - VEGETAL_DECORATION
     - TOP_LAYER_MODIFICATION

We are currently using SURFACE_STRUCTURES. The issue arises due to a single chunk going through all stages before the next chunk (maybe not always). If a tree is generated in a chunk we have flattened, but overlaps into one we have not yet flattened this means the tree is in an unnatural position on the land. In addition to this, when we come to process this new chunk, the tree has affected the heightmap >:( !!! The current work around is almost there, but I will probably need to implement something very different to actually fix the problem.
