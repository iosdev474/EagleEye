fs = require("fs");
demofile = require("demofile");

fs.readFile(process.argv[2], (err, buffer) => {
  const demoFile = new demofile.DemoFile();
  myID = -1;
  demoFile.stringTables.on("update", e => {
    if (e.table.name === "userinfo" && e.userData != null) {
      if (e.userData.name == process.argv[3]) {
        myID = e.userData.userId;
      }
    }
  });
  demoFile.gameEvents.on("player_death", e => {
    if (myID != -1 && e.attacker == myID) {
      const attacker = demoFile.entities.getByUserId(e.attacker);
      const attackerName = attacker ? attacker.name : "unnamed";
      const victim = demoFile.entities.getByUserId(e.userid);
      const victimName = victim ? victim.name : "unnamed";
      //name,team,kills,deaths,attacker_x,attacker_y,attacker_z,attacker_pitch,attacker_yaw,attacker_ducked,victim_x,victim_y,victim_z,victim_pitch,victim_yaw,victim_ducked,wallbang,isHeadshot,isHacker
      data = attackerName + "," + attacker.teamNumber + "," + attacker.kills + "," + attacker.deaths + "," + attacker.position.x + "," + attacker.position.y + "," + attacker.position.z + "," + attacker.eyeAngles.pitch + "," + attacker.eyeAngles.yaw + "," + (attacker.isDucked ? "1" : "0") + "," + victim.position.x + "," + victim.position.y + "," + victim.position.z + "," + victim.eyeAngles.pitch + "," + victim.eyeAngles.yaw + "," + (victim.isDucked ? "1" : "0") + "," + e.penetrated + "," + (e.headshot ? "1" : "0") + "," + "0" + "\n";
      /*if (!fs.existsSync(process.argv[4])) {
        header = "name,team,kills,deaths,attacker_x,attacker_y,attacker_z,attacker_pitch,attacker_yaw,attacker_ducked,victim_x,victim_y,victim_z,victim_pitch,victim_yaw,victim_ducked,wallbang,isHeadshot,isHacker\n";
        fs.appendFile(process.argv[4], header, (err) => {
          if (err) console.log(err);
          console.log(header + "Successfully Written to File.\n");
        });
      }*/
      fs.appendFile(process.argv[4], data, (err) => {
        if (err) console.log(err);
        console.log(data + "Successfully Written to File.\n");
      });
    }
  });

  demoFile.parse(buffer);
});