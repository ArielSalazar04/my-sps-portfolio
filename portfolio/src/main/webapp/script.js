// Copyright 2019 Google LLC
//
// Licensed under the Apache License, Version 2.0 (the "License");
// you may not use this file except in compliance with the License.
// You may obtain a copy of the License at
//
//     https://www.apache.org/licenses/LICENSE-2.0
//
// Unless required by applicable law or agreed to in writing, software
// distributed under the License is distributed on an "AS IS" BASIS,
// WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
// See the License for the specific language governing permissions and
// limitations under the License.

/**
 * Adds a random greeting to the page.
 */
function addRandomGreeting() {
  const quotes = ["/images/quote1.jpg", "/images/quote2.png", "/images/quote3.jpg"];
  document.getElementById("myImage").src = quotes[Math.floor(Math.random() * quotes.length)];
  // Picks a random quote and adds it to the page
}

function getData() {
  fetch('/data').then(response => response.text()).then((msg) => {
    document.getElementById('quote-container').innerText = msg;
  });
}