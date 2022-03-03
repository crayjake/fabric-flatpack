# Flatpack - A superflat mod that piggybacks vanilla generation
## Features
At the moment the overworld is flat dimension. This is very early in development so there may be crashes/bugs.

### Working features 😍
I think all above ground features are placed correctly/show up on the surface in some way. Unwanted behaviour is listed below
| Feature | Image |
| ------- | ----- |
| village |  ![village](https://github.com/crayjake/fabric-flatpack/blob/d56aeb3f51316c38c8e9c075a7818041694be6aa/images/village.png) |
| desert_village | ![desert_village](https://github.com/crayjake/fabric-flatpack/blob/d56aeb3f51316c38c8e9c075a7818041694be6aa/images/desert_village.png) |
| igloo | ![igloo](https://github.com/crayjake/fabric-flatpack/blob/d56aeb3f51316c38c8e9c075a7818041694be6aa/images/igloo.png) |
| jungle_temple | ![jungle_temple](https://github.com/crayjake/fabric-flatpack/blob/d56aeb3f51316c38c8e9c075a7818041694be6aa/images/jungle_temple.png) |
| mansion | ![mansion](https://github.com/crayjake/fabric-flatpack/blob/d56aeb3f51316c38c8e9c075a7818041694be6aa/images/mansion.png) |
| pillager_outpost | ![pillager_outpost](https://github.com/crayjake/fabric-flatpack/blob/562c3015d950ef7d8ae31817881d506153c62002/images/pillager2.png) |
| ruined_portal | ![ruined_portal](https://github.com/crayjake/fabric-flatpack/blob/d56aeb3f51316c38c8e9c075a7818041694be6aa/images/ruined_portal.png) |
| swamp_hut | ![swamp_hut](https://github.com/crayjake/fabric-flatpack/blob/d56aeb3f51316c38c8e9c075a7818041694be6aa/images/swamp_hut.png) |
| stronghold | ![stronghold](https://github.com/crayjake/fabric-flatpack/blob/d56aeb3f51316c38c8e9c075a7818041694be6aa/images/stronghold2.png) |

### Janky Generation 🥴
| Jank | Description | Image | 
| ---- | ----------- | ----- | 
| desert_temple | Spawns underground, with spiral like feature above ground (pretty cool...) | ![desert_temple_above](https://github.com/crayjake/fabric-flatpack/blob/d56aeb3f51316c38c8e9c075a7818041694be6aa/images/janky_desert_pyramid1.png) ![desert_temple_below](https://github.com/crayjake/fabric-flatpack/blob/d56aeb3f51316c38c8e9c075a7818041694be6aa/images/janky_desert_pyramid2.png) |
| village | Some houses spawn incorrectly but still better than vanilla eh? | ![village](https://github.com/crayjake/fabric-flatpack/blob/d56aeb3f51316c38c8e9c075a7818041694be6aa/images/janky_village.png) |
| trees | Especially above jungles this is common (explained in bugs below) | ![tree](https://github.com/crayjake/fabric-flatpack/blob/d56aeb3f51316c38c8e9c075a7818041694be6aa/images/janky_tree.png) |

### Bedrock Caves
Interestingly my generation leaves behind bedrock caves (i.e. caves made out of bedrock) below the bedrock layer (I believe there may be entrances without needing to break bedrock). These will not affect performance as mobs will not be able to spawn on bedrock, but they look awesome (and are spawnproof).
![bedrock_caves](https://github.com/crayjake/fabric-flatpack/blob/d56aeb3f51316c38c8e9c075a7818041694be6aa/images/bedrock_caves.png)
