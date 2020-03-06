import React, { Component } from "react";
import Virus from "./virus.png";
import "./App.css";
//import axios from "axios";
import L from "leaflet";
import { Map, Marker, Popup, TileLayer } from "react-leaflet";
import "leaflet/dist/leaflet.css";
//import SockJS from "sockjs-client";
import { Stomp } from "@stomp/stompjs";

delete L.Icon.Default.prototype._getIconUrl;

L.Icon.Default.mergeOptions({
  iconRetinaUrl: require("leaflet/dist/images/marker-icon-2x.png"),
  iconUrl: require("leaflet/dist/images/marker-icon.png"),
  shadowUrl: require("leaflet/dist/images/marker-shadow.png")
});

class App extends Component {
  state = {
    data: {
      list: [],
      num: 0,
      lastUpdate: ""
    }
  };

  async componentDidMount() {
    const url = "ws://localhost:8080/gs-guide-websocket/websocket";
    let client = Stomp.client(url);
    client.connect("", frame => {
      console.log("Connected: " + frame);
      client.subscribe("/topic/corona", data => {
        console.log("right here");
        this.setState({ data });
        console.log("body of the request", data);
      });
    });

    /*
    const socket = new SockJS("http://localhost:8080/gs-guide-websocket");
    stompClient.connect({}, frame => {
      console.log("Connection established!");
      console.log("Connected: " + frame);
      stompClient.subscribe("/topic/corona", data => {
        this.setState({ data });
        console.log("body of the request", data);
      });
    });
    */

    /*
    console.log("App.jsx");
    try {
      const data = (await axios.get("http://localhost:8080/status")).data;
      this.setState({ data });
      console.log("body of the request", data);
    } catch (error) {
      console.log("something went wrong with the called server");
    }
    */
  }

  render() {
    const position = [47, 1];
    return (
      <div className="App">
        <header className="App-header">
          <img src={Virus} className="App-logo" alt="logo" />
          <p>
            A daily <code>updates</code> on the Corona Virus states over the
            world
          </p>
          <a
            className="App-link"
            href="https://www.who.int/health-topics/coronavirus"
            target="_blank"
            rel="noopener noreferrer"
          >
            learn more...
          </a>
        </header>
        <div className="card text-center">
          <div className="card-header text-muted" />

          <div className="card-body">
            <h5 className="card-title">
              Total number of confirmed effected people
            </h5>
            <b className="card-text">{this.state.data.totateff}</b>
          </div>
          <div className="card-footer text-muted">
            Last update was on: {this.state.data.lastUpdate}
          </div>
        </div>
        <hr />
        <Map className="container" center={position} zoom={3}>
          <TileLayer
            url="https://api.maptiler.com/maps/hybrid/{z}/{x}/{y}.jpg?key=EhU44wnux0s5MUYA8ivM"
            attribution='&copy; <a href="http://osm.org/copyright">OpenStreetMap</a> contributors'
          />
          {this.state.data.list.map((el, index) => (
            <Marker key={index} position={[el.latitude, el.longtitude]}>
              {" "}
              <Popup>
                this state has: <br /> {el.lastTotalCases} effected.
              </Popup>
            </Marker>
          ))}
        </Map>
        <hr />
        <div>
          {this.state.data.list.length === 0 ? (
            <code>Loading data...</code>
          ) : (
            <table className="table container">
              <thead>
                <tr className="bg-info">
                  <th scope="col">Province/State</th>
                  <th scope="col">Country/Region</th>
                  <th scope="col">Number of effected confirmed</th>
                </tr>
              </thead>
              <tbody>
                {this.state.data.list.map((data, index) => (
                  <tr key={index}>
                    <td>
                      {data.state === "" ? (
                        <small style={{ color: "red" }}>Not Applicable</small>
                      ) : (
                        data.state
                      )}
                    </td>
                    <td>
                      {data.country === "Israel" ? (
                        <b>Palestine</b>
                      ) : (
                        data.country
                      )}
                    </td>
                    <td>{data.lastTotalCases}</td>
                  </tr>
                ))}
              </tbody>
            </table>
          )}
        </div>
      </div>
    );
  }
}

export default App;
